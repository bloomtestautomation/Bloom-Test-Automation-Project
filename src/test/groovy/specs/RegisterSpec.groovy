import spock.lang.*
import geb.*
import geb.spock.*

class RegisterSpec extends GebReportingSpec {

    def "complete registration and verify confirmed registration"() {

        when: "at registration page"
        to RegistrationPage
        verifyAt()

        and: "register a user"
        def userCreds = registerUser()

        then: "verify user credentials on participant page"
        at ParticipantPage
        verifyUserCreds(userCreds)

        when: "register a participant"
        def participantCreds = registerParticipant ([
                                gender: "Female",
                                agegroup: "Elementary (grade 1-5)",
                                session: "Creepy Crawlers",
                                frequency: "1 day"
                                ])

        and: "verify participant credentials"
        at RegistrationConfirmationPage
        verifyParticipantCreds(participantCreds)

        then: "confirm registration"
        confirmRegistration()

        when: "login to admin page"
        to AdminLoginPage
        verifyAt()
        adminLogIn()

        and: "find the registration"
        to AdminRegistrationPage
        verifyAt()
        findRegistration(userCreds)

        then: "verify registration is confirmed"
        verifyRegistration("confirmed")
                        
    }

}

import spock.lang.*
import geb.*
import geb.spock.*

class PartialRegisterSpec extends GebReportingSpec {

    def "partial registration and verify unconfirmed registration"() {

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
                                agegroup: "Preschool (ages 4-5)",
                                session: "Dinosaurs!",
                                frequency: "All 5 days"
                                ])

        and: "verify participant credentials"
        at RegistrationConfirmationPage
        verifyParticipantCreds(participantCreds)

        then: "close registration"
        closeRegistrationButton.click()

        when: "login to admin page"
        to AdminLoginPage
        verifyAt()
        adminLogIn()

        and: "find the registration"
        to AdminRegistrationPage
        verifyAt()
        findRegistration(userCreds)

        then: "verify registration is unconfirmed"
        verifyRegistration("unconfirmed")
                        
    }

}

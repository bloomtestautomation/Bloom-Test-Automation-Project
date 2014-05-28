import spock.lang.*
import geb.*
import geb.spock.*

class PriceCombinationSpec extends GebReportingSpec {

    def "complete registration, register participants, and verify price combination"() {

        when: "at registration page"
        to RegistrationPage
        verifyAt()

        and: "register a user"
        def userCreds = registerUser()

        then: "verify user credentials on participant page"
        at ParticipantPage
        verifyUserCreds(userCreds)

        when: "register a 200 dollar participant"
        def twoHundred = particpantTwoHundred()
        def twoHundredCreds = registerParticipant(twoHundred)

        and: "verify 200 dollar participant credentials"
        at RegistrationConfirmationPage
        verifyParticipantCreds(twoHundredCreds)

        then: "add another participant"
        addParticipantButton.click()
        at ParticipantPage

        when: "register an 80 dollar participant"
        def eighty = participantEighty()
        def eightyCreds = registerParticipant(eighty)

        and: "verify 80 dollar participant credentials"
        at RegistrationConfirmationPage
        verifyParticipantCreds(eightyCreds)

        then: "confirm registration"
        confirmRegistration()

        when: "login to admin page"
        to AdminLoginPage
        verifyAt()
        adminLogIn()

        and: "find the registration participant details"
        to AdminRegistrationPage
        verifyAt()
        findRegistrationParticipants(userCreds)

        then: "verify registration price combination totals 280 dollars"
        verifyPriceTotal("280.00")
                     
    }

    def particpantTwoHundred() {
        [
        gender: "Male",
        agegroup: "Infant (age 0-1)",
        session: "Creepy Crawlers",
        frequency: "All 5 days"
        ]
    }

    def participantEighty() {
        [
        gender: "Female",
        agegroup: "Elementary (grade 1-5)",
        session: "Creepy Crawlers",
        frequency: "1 day"
        ]
    }

}

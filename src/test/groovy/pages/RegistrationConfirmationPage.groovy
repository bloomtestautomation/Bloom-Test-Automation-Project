import geb.*

class RegistrationConfirmationPage extends Page {

    static at = { title == "Registration" }
    static content = {
        addParticipantButton { $("input", value: "Add Participant") }
        confirmRegistrationButton { $("input", value: "Confirm Registration") }
        closeRegistrationButton { $("input", value: "Close Registration") }
    }

    def verifyParticipantCreds(participantCreds) {
        // key off div "row panel" of the participant we are validating
        def div = $("div", class:"row panel").has("strong", text: participantCreds.get("firstname"))

        // now use the div for verification
        div.has("strong", text: participantCreds.get("firstname"))
        div.has("strong", text: participantCreds.get("lastname"))
        div.has("strong", text: participantCreds.get("nickname"))
        div.has("strong", text: participantCreds.get("gender"))
        div.has("strong", text: participantCreds.get("agegroup"))
        div.has("strong", text: participantCreds.get("session"))
        div.has("strong", text: participantCreds.get("frequency"))
    }

    def confirmRegistration() {
        confirmRegistrationButton.click()
        $("input", value: "Confirm").click()
    }

}
import geb.*

class ParticipantPage extends Page {

    static at = { title == "Participant" }
    static content = {
    	firstName { $("input", id: "FirstName") }
    	lastName  { $("input", id: "LastName") }
    	nickname  { $("input", id: "ChineseName") }
        saveButton { $("input", value: "Save") }
        closeRegistrationButton { $("input", value: "Close Registration") }
    }

    def clickRadioButtonLabel(text) {
        $("label", text: contains(text)).click() 
    }

    def verifyUserCreds(userCreds) {
        $("h5.a", text: contains(userCreds.get("email")))
        $("h5", text: contains(userCreds.get("phone")))
    }

    def registerParticipant(participant) {
        def name = generateName()
        firstName.value name.get("firstname")
        lastName.value name.get("lastname")
        nickname.value name.get("nickname")

        for (p in participant) {
            clickRadioButtonLabel(p.value)
        }

        saveButton.click()

        // return participant creds for verification
        participant + name
    }

    def generateName() {
        def date = new Date()
        def timestamp = date.time.toString()
        [firstname:"John" + timestamp, lastname:"Doe" + timestamp, nickname:"JD" + timestamp]
    }

}
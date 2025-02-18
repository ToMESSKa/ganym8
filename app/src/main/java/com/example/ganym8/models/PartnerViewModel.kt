import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.ganym8.models.Partner

class PartnerViewModel : ViewModel() {
    var newName = mutableStateOf("")
    var newInstagram = mutableStateOf("")
    var newOccupation = mutableStateOf("")
    var newPhoneNumber = mutableStateOf("")
    var newNote = mutableStateOf("")
    var selectedPartners = mutableStateOf(listOf<Partner>())

    fun updateName(name: String) { newName.value = name }
    fun updateInstagram(instagram: String) { newInstagram.value = instagram }
    fun updateOccupation(occupation: String) { newOccupation.value = occupation }
    fun updatePhoneNumber(phoneNumber: String) { newPhoneNumber.value = phoneNumber }
    fun updateNote(note: String) { newNote.value = note }
    fun updateSelectedPartners(partners: List<Partner>) { selectedPartners.value = partners }
}

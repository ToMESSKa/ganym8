import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import com.example.ganym8.models.Partner

class PartnerViewModel : ViewModel() {
    private val _partners = mutableStateListOf<Partner>()

    fun addPartner(partner: Partner) {
        _partners.add(partner)
    }

    fun getPartners(): List<Partner> {
        return _partners
    }
}

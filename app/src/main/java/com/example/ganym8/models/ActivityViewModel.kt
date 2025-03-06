import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import com.example.ganym8.models.Activity
import com.example.ganym8.models.Partner

class ActivityViewModel : ViewModel() {
    private val _activities = mutableStateListOf<Activity>()

    fun addPartner(activty: Activity) {
        _activities.add(activty)
    }

    fun getActivites(): List<Activity> {
        return _activities
    }
}

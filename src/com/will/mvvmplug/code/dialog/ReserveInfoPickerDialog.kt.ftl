package ${packageName}.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ${packageName}.R
import ${packageName}.BR
import ${packageName}.ui.viewmodel.${viewmodelName}
import ${packageName}.databinding.${baseBinding}
import com.xmotion.habit.base.BaseDialogFragment
import com.xmotion.habit.base.BaseDialogViewModel


<@gb.fileHeader />
class ${activityClass}  : BaseDialogFragment<${baseBinding}, ${viewmodelName}>() {


    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)= R.layout.${layoutName}

    override fun initVariableId(): Int = BR.viewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.BottomDialogStyle)
    }

    override fun initData() {
        super.initData()
    }

}


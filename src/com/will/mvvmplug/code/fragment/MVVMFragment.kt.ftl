package ${packageName}.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import ${packageName}.R
import ${packageName}.BR
import ${packageName}.ui.viewmodel.${viewmodelName}
import ${packageName}.databinding.${baseBinding}
import com.xmotion.habit.base.BaseFragment

<@gb.fileHeader />
class ${activityClass} : BaseFragment<${baseBinding}, ${viewmodelName}>() {

    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = R.layout.${layoutName}

	override fun initVariableId() = BR.viewModel

    override fun initData() {
        super.initData()
    }
}

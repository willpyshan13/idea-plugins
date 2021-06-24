package ${packageName}.ui.activity

import android.os.Bundle
import ${packageName}.R
import ${packageName}.BR
import ${packageName}.ui.viewmodel.${viewmodelName}
import com.xmotion.habit.databinding.CommonListLayoutBinding
import com.xmotion.habit.base.BaseActivity

<@gb.fileHeader />
class ${activityClass} : BaseActivity<${baseBinding}, ${viewmodelName}>() {

    override fun initContentView(savedInstanceState: Bundle?) = R.layout.${layoutName}

	override fun initVariableId() = BR.viewModel

    override fun initData() {
        super.initData()
    }
}

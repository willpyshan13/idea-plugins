package ${packageName}.ui.viewmodel

import android.app.Application
import com.xmotion.habit.base.BaseViewModel
import ${packageName}.repository.${repositoryPackageName}

<@gb.fileHeader />
class ${viewmodelName}(val context: Application) : BaseViewModel<${repositoryPackageName}>(context) {

}

package ${packageName}.ui.viewmodel

import android.app.Application
import com.xmotion.habit.base.BaseViewModel
import com.xmotion.habit.base.BaseDialogViewModel
import ${packageName}.repository.${repositoryPackageName}

<@gb.fileHeader />
class ${viewmodelName}(val context: Application) : BaseDialogViewModel<${repositoryPackageName}>(context) {

}

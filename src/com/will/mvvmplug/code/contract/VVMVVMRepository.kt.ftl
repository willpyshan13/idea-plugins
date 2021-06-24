package ${packageName}.repository

import com.xmotion.habit.base.BaseModel
import ${packageName}.repository.contract.${contractPackageName}

<@gb.fileHeader />
class ${repositoryPackageName} : BaseModel<${contractPackageName}.View>(), ${contractPackageName}.Model {
    
}

package ${packageName}.repository.contract

import com.xmotion.habit.base.BaseBeanModel
import com.xmotion.habit.base.BaseView

<@gb.fileHeader />
interface ${contractPackageName} {

	/**
	 * 对于经常使用的关于UI的方法
	 */	
    interface View : BaseView

	/**
	 * Model层定义接口
	 */	
    interface Model : BaseBeanModel

}

package ${packageName}.ui.activity;

import android.os.Bundle;
import ${packageName}.R;
import ${packageName}.BR;
import ${packageName}.ui.viewmodel.${viewmodelName};
import ${packageName}.databinding.${baseBinding};
import com.xmotion.habit.base.BaseActivity;

<@gb.fileHeader />
public class ${activityClass} extends BaseActivity<${baseBinding},${viewmodelName}> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.${layoutName};
    }
	@Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
    }
}

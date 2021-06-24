package ${packageName}.ui.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import com.xmotion.habit.base.BaseViewModel;
import ${packageName}.repository.contract.${contractPackageName};

<@gb.fileHeader />
public class ${viewmodelName} extends BaseViewModel implements ${contractPackageName}.View {

  
	public ${viewmodelName}(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    
}

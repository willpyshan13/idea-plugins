package com.vv.life.work.ui.viewmodel

import android.app.Application
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xmotion.habit.base.BaseListViewModel
import com.xmotion.habit.base.state.EmptyState
import com.xmotion.habit.extensions.launch
import com.xmotion.habit.widget.recyclerview.paging.LoadCallback
import me.tatarka.bindingcollectionadapter2.ItemBinding
import ${packageName}.repository.${repositoryPackageName}


<@gb.fileHeader />
class ${viewmodelName}(val context: Application) : BaseListViewModel<${repositoryPackageName}, ${vmItemName}>(context) {

    override fun onCreate() {
        super.onCreate()
        loadInit()
    }

    override fun getDiffItemCallback(): DiffUtil.ItemCallback<${vmItemName}> {
        return object : DiffUtil.ItemCallback<${vmItemName}>() {
            override fun areItemsTheSame(oldItem: ${vmItemName}, newItem: ${vmItemName}): Boolean {
                return false
            }

            override fun areContentsTheSame(oldItem: ${vmItemName}, newItem: ${vmItemName}): Boolean {
                return false
            }
        }
    }

    override fun getItemBinding(): ItemBinding<${vmItemName}> {

        return ItemBinding.of { itemBinding, position, item ->
            itemBinding.set(BR.viewModel, R.layout.list_footer_no_more)
        }
    }

    override fun loadData(pageIndex: Int, loadCallback: LoadCallback<${vmItemName}>) {

        launch({
            val list = listOf<Int>(1, 2, 3, 4)
            var listTaget = list.map {
                ${vmItemName}(this)
            }

            loadCallback.onSuccess(listTaget, 1, 2)
        }, {

        })
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    override fun showEmptyState() {
        mStateModel.setEmptyState(EmptyState.EMPTY)
    }


}

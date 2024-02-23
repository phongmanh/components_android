package com.liam.viewcomponents.adapters

import androidx.databinding.ViewDataBinding
import com.liam.viewcomponents.adapters.BindableRecyclerAdapter
import com.liam.viewcomponents.adapters.BindableViewHolder

abstract class SimpleRecyclerItem {

    abstract var adapter: BindableRecyclerAdapter
    abstract fun getLayout(): Int
    fun getViewType() = getLayout()
    abstract fun getViewHolderProvider(): (binding: ViewDataBinding) -> BindableViewHolder

}
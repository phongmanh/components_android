package com.liam.viewcomponents.adapters

import androidx.databinding.ViewDataBinding
import com.liam.viewcomponents.adapters.BindableRecyclerPagingAdapter
import com.liam.viewcomponents.adapters.BindableViewHolder

abstract class SimpleRecyclerPagingItem {
    abstract var adapter: BindableRecyclerPagingAdapter
    abstract fun getItemData(): Any
    abstract fun getItemDataId(): Any
    abstract fun getLayout(): Int
    fun getViewType() = getLayout()
    abstract fun getViewHolderProvider(): (binding: ViewDataBinding) -> BindableViewHolder

}
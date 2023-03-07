package com.giaphat.component.adapters

import androidx.databinding.ViewDataBinding
import co.kr.gogox.driver.util.adapters.BindableRecyclerAdapter

abstract class SimpleRecyclerItem {

    abstract var adapter: BindableRecyclerAdapter
    abstract fun getLayout(): Int
    fun getViewType() = getLayout()
    abstract fun getViewHolderProvider(): (binding: ViewDataBinding) -> BindableViewHolder

}
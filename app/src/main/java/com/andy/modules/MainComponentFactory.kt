package com.andy.modules

import androidx.appcompat.app.AppCompatActivity
import com.andy.modularization.Component
import com.andy.modularization.HolderIdProvider
import com.andy.modularization.Service
import com.andy.modularization.ServiceManager
import com.andy.modularization.holderId
import com.andy.modules.component.NoneUiComponent
import com.andy.modules.databinding.ActivityMainBinding
import com.andy.modules.ui.BottomBar
import com.andy.modules.ui.ContentLayout
import com.andy.modules.ui.TitleBar

class MainComponentFactory(
    private val activity: AppCompatActivity,
    private val binding: ActivityMainBinding,
) : Component.ComponentFactory {
    private val components = mutableListOf<Component>()

    override fun newComponents() {
        addComponent(TitleBar(activity, binding.titleBarContent))
        addComponent(ContentLayout(activity, binding.layoutContent))
        addComponent(BottomBar(activity, binding.bottomBarContent))
        addComponent(NoneUiComponent(activity))
    }

    private fun addComponent(component: Component) {
        components.add(component)
        if (component is Service) {
            ServiceManager.registerService(component.holderId, component)
        }
    }

    override fun components(): List<Component> {
        return components
    }

    override fun release() {
        components.forEach {
            ServiceManager.releaseHolderServices(it.holderId)
        }
        components.clear()
    }
}
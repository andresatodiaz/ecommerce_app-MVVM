package com.example.ecommmerceapp

import android.content.Context
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.core.app.ApplicationProvider
import com.example.ecommmerceapp.data.Service.ProductoService
import com.example.ecommmerceapp.data.Service.UsuarioService
import com.example.ecommmerceapp.presentation.Home.ViewModel.HomeViewModel
import com.example.ecommmerceapp.presentation.Perfil.ViewModel.PerfilViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

/*override fun newApplication(
        cl: ClassLoader?,
        className:String?,
        context: Context?
    ): Application{
        return super.newApplication(cl,HiltTestApplication::class.java.name,context)
    }*/

@HiltAndroidTest
class MainTesting {
    val context = ApplicationProvider.getApplicationContext<MainActivity>()

    @get: Rule
    var hiltRule= HiltAndroidRule(this)

    @get: Rule
    val rule = createAndroidComposeRule<MainActivity>()


    @Inject
    @Named("userService")
    lateinit var usuarioService : UsuarioService

    @Inject
    @Named("productoService")
    lateinit var productoService: ProductoService

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var perfilViewModel: PerfilViewModel

    @Before
    fun setup(){
        hiltRule.inject()
        homeViewModel= HomeViewModel(productoService,usuarioService)
        perfilViewModel = PerfilViewModel(usuarioService)
    }
    @Test
    fun openMain(){
        val sp = context.getSharedPreferences("preferences",
            Context.MODE_PRIVATE
        ).edit()
        sp.putString("LOGGED_ID","c2816877-a027-4527-852c-20c2d33e2895")
        sp.apply()

        rule.waitUntil {
            rule.onNodeWithTag("homeTag").fetchSemanticsNode().size.equals(1)
        }
    }



}
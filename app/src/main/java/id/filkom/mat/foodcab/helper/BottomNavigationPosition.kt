package id.filkom.mat.foodcab.helper

import android.support.v4.app.Fragment
import id.filkom.mat.foodcab.R
import id.filkom.mat.foodcab.HomeFragment
import id.filkom.mat.foodcab.ProfileFragment
import id.filkom.mat.foodcab.KategoriFragment
import id.filkom.mat.foodcab.LanggananFragment
/**
 * Created by mat on 5/12/18.
 */

enum class BottomNavigationPosition(val position: Int, val id: Int) {
    HOME(0, R.id.home),
    KATEGORI(1, R.id.kategori),
    LANGGANAN(2, R.id.langganan),
    PROFILE(3, R.id.profile);
}

fun findNavigationPositionById(id: Int): BottomNavigationPosition = when (id) {
    BottomNavigationPosition.HOME.id -> BottomNavigationPosition.HOME
    BottomNavigationPosition.KATEGORI.id -> BottomNavigationPosition.KATEGORI
    BottomNavigationPosition.LANGGANAN.id -> BottomNavigationPosition.LANGGANAN
    BottomNavigationPosition.PROFILE.id -> BottomNavigationPosition.PROFILE
    else -> BottomNavigationPosition.HOME
}

fun BottomNavigationPosition.createFragment(): Fragment = when (this) {
    BottomNavigationPosition.HOME -> HomeFragment.newInstance()
    BottomNavigationPosition.KATEGORI -> KategoriFragment.newInstance()
    BottomNavigationPosition.LANGGANAN -> LanggananFragment.newInstance()
    BottomNavigationPosition.PROFILE -> ProfileFragment.newInstance()
}

fun BottomNavigationPosition.getTag(): String = when (this) {
    BottomNavigationPosition.HOME -> HomeFragment.TAG
    BottomNavigationPosition.KATEGORI -> KategoriFragment.TAG
    BottomNavigationPosition.LANGGANAN -> LanggananFragment.TAG
    BottomNavigationPosition.PROFILE -> ProfileFragment.TAG
}
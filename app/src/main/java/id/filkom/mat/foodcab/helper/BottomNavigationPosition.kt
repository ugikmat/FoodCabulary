package id.filkom.mat.foodcab.helper

import android.support.v4.app.Fragment
import id.filkom.mat.foodcab.R
import id.filkom.mat.foodcab.HomeFragment
import id.filkom.mat.foodcab.ProfileFragment
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
    BottomNavigationPosition.HOME -> HomeFragment.newInstance(1)
    BottomNavigationPosition.KATEGORI -> HomeFragment.newInstance(1)
    BottomNavigationPosition.LANGGANAN -> HomeFragment.newInstance(1)
    BottomNavigationPosition.PROFILE -> ProfileFragment.newInstance()
}

fun BottomNavigationPosition.getTag(): String = when (this) {
    BottomNavigationPosition.HOME -> HomeFragment.TAG
    BottomNavigationPosition.KATEGORI -> HomeFragment.TAG
    BottomNavigationPosition.LANGGANAN -> HomeFragment.TAG
    BottomNavigationPosition.PROFILE -> ProfileFragment.TAG
}
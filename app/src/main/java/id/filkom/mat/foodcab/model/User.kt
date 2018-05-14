package id.filkom.mat.foodcab.model

import java.util.ArrayList

/**
 * Created by mat on 5/13/18.
 */
class User {
    companion object Factory {
        fun create(): User = User()
    }
    var name: String? = null
    var fav: MutableList<Food> = ArrayList<Food>()

}
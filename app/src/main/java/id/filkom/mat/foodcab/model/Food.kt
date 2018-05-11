package id.filkom.mat.foodcab.model

/**
 * Created by mat on 5/9/18.
 */
class Food {
    companion object Factory {
        fun create(): Food = Food()
    }
    var id: String? = null
    var name: String? = null
    var category: String? = null
    var image: String? = null
    var location: String? = null
    var seller: String? = null
    var phone: String? =null
}
package id.filkom.mat.foodcab.model

/**
 * Created by mat on 5/9/18.
 */
class Dummy {
    companion object Factory {
        fun create(): Dummy = Dummy()
    }
    //this is dummy
    open var id:Int = 0

    var objectId: String? = null
    var itemText: String? = null
    var done: Boolean? = false
}

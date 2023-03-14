package com.giaphat.viewcomponents.views

interface DisplayName {

    val displayName: String

}

interface BreadcrumbDisplay : DisplayName {

    companion object {
        const val SLASH = "/"
    }

    override val displayName: String
        get() {
            return parent?.let {
                "${it.displayName} $SLASH $myOwnDisplayName"
            } ?: run {
                myOwnDisplayName
            }
        }

    var parent: BreadcrumbDisplay?
    val myOwnDisplayName: String
}
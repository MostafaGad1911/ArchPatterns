package com.apiTask.AppsSquare.Helper

class Helpers {
    companion object{
        fun String.emailValid(): Boolean {
            if (this.contains("@") && this.contains(".") && !this.endsWith("@") && !this.endsWith("."))
                return true
            return false
        }
    }
}
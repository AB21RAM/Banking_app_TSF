package com.example.tsfinternsbank.Model

data class transactions(val id : Int? ,
                        val sender : String ,
                        val receiver : String?,
                        val amount : Int)

package com.example.optuspoc.model

/*Model class contains  API response data model*/
class UserAddress(
    var zipcode: String,
    var geo: UserGeo,
    var suite: String,
    var city: String,
    var street: String
)

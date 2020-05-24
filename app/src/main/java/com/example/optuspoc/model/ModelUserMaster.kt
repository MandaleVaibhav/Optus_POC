package com.example.optuspoc.model

/*Model class contains entire API response data model*/
class ModelUserMaster(
    var website: String,
    var address: UserAddress,
    var phone: String,
    var name: String,
    var company: UserCompany,
    var id: String,
    var email: String,
    var username: String
)

class UserAddress(
    var zipcode: String,
    var geo: UserGeo,
    var suite: String,
    var city: String,
    var street: String
)

class UserCompany(
    var bs: String,
    var catchPhrase: String,
    var name: String
)

class UserGeo(
    var lng: String,
    var lat: String
)

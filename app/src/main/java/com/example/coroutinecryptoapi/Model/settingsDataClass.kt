package com.example.coroutinecryptoapi.Model

data class settingsDataClass(
    var hesapBilgileri:accountInfo,
    var sifreDegistir:sifreDegistir,
    var oncekiİslemler:oncekliIslemler,

)
data class accountInfo(
    var hesapBakiye:Double,
    var toplamKazanc:Double,
    var islemTarihleri:String,
    var kaydettiğimCoinler:kayitliCoinler
)

data class sifreDegistir(
    var gecerlSifre:String,
    var gecerliSifreTekrar:String,
    var yeniSifre:String
)

data class oncekliIslemler(
    var satinAlinancoinler:ArrayList<String>,
    var islemTarihleri:ArrayList<String>,
)
data class kayitliCoinler(
    var kaydedilenCoinler:ArrayList<String>,
    var kayıtlıTarihler:ArrayList<String>,
    var kaydedilenGünküDeğerleri:ArrayList<Double>,
    var guncelDeğerler:ArrayList<Double>
){

}

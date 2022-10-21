package com.example.ragil.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainActivityModel: ViewModel() {

    private val _nombreCliente= mutableStateOf("Null")
    val nombreCliente : State<String> = _nombreCliente

    private val _nombreVehiculo= mutableStateOf("Null")
    val nombreVehiculo : State<String> = _nombreVehiculo

    private val _precioVehiculo= mutableStateOf(0.0)
    val precioVehiculo : State<Double> = _precioVehiculo

    private val _porcentajeEnganche= mutableStateOf(0.0)
    val porcentajeEnganche : State<Double> = _porcentajeEnganche

    private val _anioFinanciamiento= mutableStateOf(0)
    val anioFinanciamiento : State<Int> = _anioFinanciamiento

    private val _porcentajeFinanciamiento= mutableStateOf(0.0)
    val porcentajeFinanciamiento : State<Double> = _porcentajeFinanciamiento


    fun setName(name:String){
        if (name=="")
            _nombreCliente.value = "Null"
        else
            _nombreCliente.value = name
    }

    fun setBrand(Brand:String,price:Double){
        _nombreVehiculo.value = Brand
        _precioVehiculo.value = price
    }

    fun setDownPay(percentageDownPay:Double){
        _porcentajeEnganche.value = percentageDownPay
    }

    fun setFinancing(year:Int,percentageFinancing:Double){
        _anioFinanciamiento.value = year
        _porcentajeFinanciamiento.value = percentageFinancing
    }


    fun reset(){
        _nombreCliente.value = "Null"
        _nombreVehiculo.value = "Null"
        _precioVehiculo.value = 0.0
        _porcentajeEnganche.value = 0.0
        _anioFinanciamiento.value = 0
        _porcentajeFinanciamiento.value = 0.0
    }

    fun generateQuote():String{
        var downPay = _precioVehiculo.value * (_porcentajeEnganche.value/100)
        var amountToFinance = _precioVehiculo.value - downPay
        var interestPerYear = amountToFinance * (_porcentajeFinanciamiento.value/100)
        var interest = interestPerYear * _anioFinanciamiento.value
        var amountToFinanceAndInterest = amountToFinance + interest
        var monthlyPayment = amountToFinanceAndInterest / (_anioFinanciamiento.value * 12)
        var totalCost=  downPay + amountToFinanceAndInterest

        return "Cliente:: ${_nombreCliente.value}\n" +
                "Vehiculo:: ${_nombreVehiculo.value} \$ ${_precioVehiculo.value}\n" +
                "Enganche:: (${_porcentajeEnganche.value}%) de ${downPay}\n" +
                "Monto a financiar:: $${amountToFinance}\n" +
                "Financiamiento:: a ${_anioFinanciamiento.value} años, tasa del ${_porcentajeFinanciamiento.value}%\n" +
                "Monto de Intereses:: por ${_anioFinanciamiento.value} años = $${interest}\n" +
                "Monto a financiar + interes:: = $${amountToFinance} + $${interest} = $${amountToFinanceAndInterest}\n" +
                "Pagos mensuales por:: $${monthlyPayment}\n" +
                "Costo total:: (Enganche+Financiamiento), $${downPay} + $${amountToFinanceAndInterest}= $${totalCost}"
    }



}
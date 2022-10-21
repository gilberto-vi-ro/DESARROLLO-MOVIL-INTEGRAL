package com.example.ragil.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.ragil.R
import com.example.ragil.model.MainActivityModel
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.setValue



class MainActivity : ComponentActivity() {

    val object_activity_model: MainActivityModel by viewModels()

    private val _textInput= mutableStateOf("")
    val textInput : State<String> = _textInput

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            preView ()
        }
    }

    @Composable
    fun preView() {
        Column(modifier = Modifier
            .fillMaxHeight()
            .background(colorHex("#E8F5FB"))
        ){

            title("Cotizador de Autos Nuevos")
            myImg()
            groupName()
            groupBrand()
            groupDownPay()
            groupFinancing()
            btns()
        }
    }

    @Composable
    fun title(name: String) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorHex("#298D7A")),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){

            Text(text = "$name",
                color=colorHex("#E8F5FB"),
                style= MaterialTheme.typography.subtitle1,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
                    //.align(Alignment.Top),

            )
        }
    }

    @Composable
    fun myImg()
    {
        Image(
            painterResource(id = R.drawable.cotiza_auto2),
            contentDescription = "Img de perfil",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .clip(CircleShape)
//                .size(100.dp)
        )
    }

    @Composable
    fun groupName() {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            //Get data

            Text(text = "Nombre:",
                color=colorHex("#298D7A"),
                fontWeight = FontWeight.W900,
                fontSize = 16.sp,
                modifier = Modifier.padding(top=18.dp,end = 10.dp)
            )

            TextField(
                value = textInput.value,
                //keyboardOptions = KeyboardOptions(keyboardType= KeyboardType.Number),
                //modifier = Modifier.width(240.dp),
                onValueChange =
                {
                    _textInput.value = it
                }
            )
            object_activity_model.setName(textInput.value)

        }
    }

    @Composable
    fun groupBrand() {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            //Get data

            Text(text = "Marca:",
                color=colorHex("#298D7A"),
                fontWeight = FontWeight.W900,
                fontSize = 16.sp,
                modifier = Modifier.padding(top=13.dp)
            )
            //SPINER

            var expanded by remember { mutableStateOf(false) }

            val suggestions = arrayOf(
                //index, item-price,item, price
                arrayOf(0, "Honda Accord $678,026.22","Honda Accord", 678026.22),
                arrayOf(1, "Vw Touareg   $879,266.15","Vw Touareg",879266.15),
                arrayOf(2, "BMW X5       $1,025,366.87","BMW X5",1025366.87),
                arrayOf(3, "Mazda CX7    $988,641.02","Mazda CX7",988641.02),
            )

            Box() {
                Button(onClick = { expanded = !expanded }){
                    Text ("Seleccione una Marca")
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    suggestions.forEach { label ->
                        DropdownMenuItem(onClick = {
                            expanded = false
                            //action for click
                            object_activity_model.setBrand(label[2].toString(),label[3].toString().toDouble())
                        }) {
                            Text(text = label[1].toString())
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun groupDownPay() {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            //Get data

            Text(text = "Enganche:",
                color=colorHex("#298D7A"),
                fontWeight = FontWeight.W900,
                fontSize = 16.sp,
                modifier = Modifier.padding(top=13.dp)
            )
            //SPINER

            var expanded by remember { mutableStateOf(false) }

            val suggestions = arrayOf(
                //index, item, percentage
                arrayOf(0, "20%", 20),
                arrayOf(1, "40%", 40),
                arrayOf(2, "60%", 60),
            )

            Box() {
                Button(onClick = { expanded = !expanded }){
                    Text ("Seleccione Enganche")
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    suggestions.forEach { label ->
                        DropdownMenuItem(onClick = {
                            expanded = false
                            //action for click
                            object_activity_model.setDownPay(label[2].toString().toDouble())
                        }) {
                            Text(text = label[1].toString())
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun groupFinancing() {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            //Get data

            Text(text = "Financiamiento: \n    (anual)",
                color=colorHex("#298D7A"),
                fontWeight = FontWeight.W900,
                fontSize = 16.sp,
                modifier = Modifier.padding(top=10.dp)
            )
            //SPINER

            var expanded by remember { mutableStateOf(false) }

            val suggestions = arrayOf(
                //index,item,year,porcentaje
                arrayOf(0, "1 año al 7.5%", 1 , 7.5),
                arrayOf(1, "2 años al 9.5%", 2, 9.5),
                arrayOf(2, "3 años al 10.3%", 3, 10.3),
                arrayOf(3, "4 años al 12.6%", 4, 12.6),
                arrayOf(4, "5 años al 13.5%", 5, 13.5),
            )

            Box() {
                Button(onClick = { expanded = !expanded }){
                    Text ("Seleccione Plazo")
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    suggestions.forEach { label ->
                        DropdownMenuItem(onClick = {
                            expanded = false
                            //action for click
                            object_activity_model.setFinancing(label[2].toString().toInt(),label[3].toString().toDouble())
                        }) {
                            Text(text = label[1].toString())
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun btns() {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            AlertDialogSample()

            Button(onClick = {
                //your onclick code here
                _textInput.value  = ""
                object_activity_model.reset()
            }) {
                Text(text = "Reset")
            }
        }
    }

    /**
     * Crea un diálogo de alerta sencillo
     * @return Nuevo diálogo
     */
    @Composable
    fun AlertDialogSample() {
        var Quote = object_activity_model.generateQuote().split("\n")
        MaterialTheme {
            Column {
                val openDialog = remember { mutableStateOf(false)  }

                Button(onClick = {
                    openDialog.value = true
                }) {
                    Text("Cotizar")
                }

                if (openDialog.value) {

                    AlertDialog(
                        onDismissRequest = {
                            // Dismiss the dialog when the user clicks outside the dialog or on the back
                            // button. If you want to disable that functionality, simply use an empty
                            // onCloseRequest.
                            openDialog.value = false
                        },
                        title = {
                            Text(text = "FINANCIAMIENTO:",
                                color=colorHex("#298D7A"),
                                fontWeight = FontWeight.W900,
                                fontSize = 16.sp,
                            )
                        },
                        text = {
                            Column() {
                                Quote.forEach { textValue ->
                                        var value = textValue.split("::")
                                        Text(text = value[0].toString()+":",
                                            color=colorHex("#298D7A"),
                                            fontWeight = FontWeight.W900,
                                            fontSize = 16.sp,
                                        )
                                        Text(text = value[1].toString(),
                                            fontSize = 16.sp,
                                        )
                                }

                            }
                        },
                        confirmButton = {
//                            Button(
//
//                                onClick = {
//                                    openDialog.value = false
//                                }) {
//                                Text("This is the Confirm Button")
//                            }
                        },
                        dismissButton = {
                            Button(

                                onClick = {
                                    openDialog.value = false
                                }) {
                                Text("Cerrar")
                            }
                        }
                    )
                }
            }

        }
    }

    fun  colorHex(color:String):Color {
        return Color(android.graphics.Color.parseColor(color))
    }
}


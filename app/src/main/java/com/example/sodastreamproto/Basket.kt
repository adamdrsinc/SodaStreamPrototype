package com.example.sodastreamproto

class Basket {
    companion object{
        private val basketDrinks : ArrayList<Drink> = arrayListOf()

        fun addDrink(drink: Drink){
            var alreadyDrink = basketDrinks.find{
                it.drinkName == drink.drinkName
            }

            if(alreadyDrink == null){
                basketDrinks.add(drink)
            }
        }

        fun getDrinks(): ArrayList<Drink>{
            return basketDrinks
        }
    }
}
# Week-2-Native
Sovelluksen polku: app > src > main > java/com/example/week2native >

Sovelluksen (APK:n) käynnistys tapahtuu Android Studion emulaattorilla. Ensimmäisen viikon tehtävästä on säilynyt task-data class sekä mock-data valmiiksi näytettäväksi, mutta UI on kokonaan siirtynyt ViewModeliin.

Jetpack Compose seuraa tässä käyttöliittymän tilaa (eli statea), eli mitä tahansa sovelluksen aikana muuttuvaa arvoa kuten vaikka tekstikentän sisältö tai valintaruudun tila tai vaikka tehtävälista. Tilan muuttuessa Compose tekee automaattisesti päivityksen UI:hin vastaamaan uutta tilaa. 

ViewModel on kätevämpi kuin remember tässä koska remember toimii vain yhden composable-komponentin sisällä, eli jos komponentti tuhotaan ja luodaan uudelleen niin tila(state) katoaa. Viewmodel taas säilyttää sen koko sovelluksen elinkaaren ajan yksittäisen komponentin sijaan, eli käytännössä remember on vain tilapäinen kun ViewModel on pysyvä niin kauan kun sovellus on päällä. 

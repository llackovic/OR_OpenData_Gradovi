# Gradovi - otvoreni skup podataka

Ovo je repozitorij koji sadrži kataloške podatke o **gradovima** i njihovim **gradskim četvrtima**. Opis dostupnih atributa za gradove i gradske četvrti te njihove vrste bit će priloženi u nastavku.

Skup podataka napravljen je u sklopu laboratorijskih vježbi iz predmeta **Otvoreno računarstvo** koji se predaje na **Fakultetu elektrotehnike i računarstva**, Sveučilišta u Zagrebu.

Repozitorij i skup podataka su pod **MIT licencom:**

> Kratka i jednostavna otvorena licenca koja zahtjeva samo očuvanje licence i autorskih prava nad proizvodom koji je originalno distribuiran pod tom licencom. Ono što je izmijenjeno može se licencirati pod drugom licencom i bez izvornog koda.
	
## Osnovne Informacije

| **Naziv skupa** | Gradovi |
| :--- | :--- |
| **Autor** | Luka Lacković |
| **Licenca** | [MIT Licence](https://opensource.org/licenses/MIT) |
| **Jezik** | hrvatski |
| **Format podataka** | CSV, JSON |
| **Verzija** | 1.0 |
| **Broj relacija** | 2 |


## Baza podataka - struktura

Baza podataka napravljena je u **PostgreSQL-u**.

Baza se sastoji od **2** relacije:
- grad
- gradska četvrt
	
Svaka gradska četvrt ima jedan grad, a svaki grad ima više gradskih četvrti. Prema tome, grad je roditelj, a gradska četvrt dijete i njihov odnos je **1:N**

### Grad

| Atribut | Tip podatka | Opis | Napomena |
| :--- | :--- | :--- | :--- |
| **id** | int | identifikacijski broj grada |  |
|**naziv**| string | ime grada |  |
|**drzava**| string | ime države na kojoj je grad | Ukoliko je grad dio više državnih cjelina uzima se ona najšira (npr. New York: USA, London: UK ) |
|**kontinent**| string | kontinent na kojem se grad nalazi | |
|**povrsina**| float | površina grada u km2 | Ako grad ima kopneni i morski dio uzima se ukupna površina |
|**nadmorska_visina**| int | nadmorska visina na kojoj se grad nalazi |  |
|**broj_stanovnika**| int | ukupan broj stanovnika grada |  |
|**gustoca_naseljenosti**| int | gustoća naseljenosti izražena u broju stanovnika po km2 ||
|**gradonacelnik**| string | ime trenutnog gradonačelnika |  |
|**web**| string | URL službenog web sjedišta grada |  |
|**vremenska_zona**| int | broj može biti pozitivan i negativan, a označava odstupanje od UTC/GMT |  |
|**glavni_grad**| bool | Da li je taj grad glavni grad države u kojoj se nalazi |  |	


### Gradska četvrt

| Atribut | Tip podatka | Opis | Napomena |
| :--- | :--- | :--- | :--- |
| **id** | int | identifikacijski broj gradske četvrti |  |
| **naziv** | string | ime gradske četvrti |  |
| **povrsina** | float | površina gradske četvrti u km2| ukupna površina |
| **broj_stanovnika** | int | ukupan broj stanovnika gradske četvrti |  |
| **gustoca_naseljenosti** | int | gustoća naseljenosti izražena u broju stanovnika po km2 |  |

	
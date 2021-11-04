# 1010-game
A népszerű 1010 játék mintájára készült. Java maven projektként.

## A játék célja hogy minél több pontot gyűjtsünk:
* egy sor vagy oszlop bettelésekor 10-10 pont jár
* minden elem után annyi pont jár amennyi négyzetből áll

## További funkcionalitások:
* A felhasználó 3x kitörölhet 1-1 sort vagy oszlopot, ha segítségre van szüksége, ez 50 pontjába kerül
* A felhasználó 3x kérhet másik 3 elemet a kapottak helyett, ha szüksége van rá, ez szintén 50 pontjába kerül

A játékos akkor veszít, ha már nincs hova lerakja az elemeket és az összes segítsége elfogyott (vagy van még segítsége, de nincs rá pontja, hogy "megvegye")


jatek/jatek-core/src/main/resources/application.properties -ben át kell írni az adatbázis elérését "út_a_gyökértől/jatek/jatek-core/src/main/resources/Eredmenyek.db"
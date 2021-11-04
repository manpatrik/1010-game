package hu.alkfejl;

import java.time.LocalTime;

public class Jatek {
    private boolean table[][] = new boolean[10][10];
    private int pontszam;
    private String elemek[];
    private String lathatoElemek[] = new String[3];
    private int ujElemSegitseg;
    private int torlesSegitseg;
    private long idoKezdes;
    private long idoVege;

    public int getUjElemSegitseg() {
        return ujElemSegitseg;
    }

    public void setUjElemSegitseg() {
        this.ujElemSegitseg --;
    }

    public int getTorlesSegitseg() {
        return torlesSegitseg;
    }

    public void setTorlesSegitseg() {
        this.torlesSegitseg --;
    }

    public void segitseg(){
        pontszam -= 50;
    }

    public long getIdoKezdes() {
        return idoKezdes;
    }

    public void setIdoKezdes(long idoKezdes) {
        this.idoKezdes = idoKezdes;
    }

    public long getIdoVege() {
        return idoVege;
    }

    public void setIdoVege(long idoVege) {
        this.idoVege = idoVege;
    }

    public int getPontszam() {
        return pontszam;
    }

    public String[] getLathatoElemek() {
        return lathatoElemek;
    }

    public void setLathatoElemNull(int index) {
        this.lathatoElemek[index] = null;
    }

    public Jatek() {
        pontszam = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                table[i][j] = false;
            }
        }
        elemek = new String[]{"V5", "V4", "V3", "V2", "F5", "F4", "F3", "F2", "N1", "N2", "N3", "LJF2", "LJF3", "LJA2", "LJA3", "LBF2", "LBF3", "LBA2", "LBA3"};
        for (int i = 0; i < lathatoElemek.length; i++) {
            lathatoElemek[i] = elemek[(int)(Math.random() * elemek.length)];
        }
        torlesSegitseg = 3;
        ujElemSegitseg = 3;
    }

    public boolean[][] getTable() {
        return table;
    }

    public int setCell(int sor, int oszlop, String mit){
        switch (mit){
            case "V5":
                if(oszlop > 1 && oszlop < 8){
                    if (!table[sor][oszlop-2] && !table[sor][oszlop-1] && !table[sor][oszlop] && !table[sor][oszlop+1] && !table[sor][oszlop+2]){
                        table[sor][oszlop-2] = true;
                        table[sor][oszlop-1] = true;
                        table[sor][oszlop] = true;
                        table[sor][oszlop+1]= true;
                        table[sor][oszlop+2]=true;
                        return 5;
                    }
                }
                return 0;
            case "V4":
                if(oszlop > 0 && oszlop < 8){
                    if (!table[sor][oszlop-1] && !table[sor][oszlop] && !table[sor][oszlop+1] && !table[sor][oszlop+2]){
                        table[sor][oszlop-1] = true;
                        table[sor][oszlop] = true;
                        table[sor][oszlop+1]= true;
                        table[sor][oszlop+2]=true;
                        return 4;
                    }
                }
                return 0;
            case "V3":
                if(oszlop > 0 && oszlop < 9){
                    if (!table[sor][oszlop-1] && !table[sor][oszlop] && !table[sor][oszlop+1]){
                        table[sor][oszlop-1] = true;
                        table[sor][oszlop] = true;
                        table[sor][oszlop+1]= true;
                        return 3;
                    }
                }
                return 0;
            case "V2":
                if(oszlop < 9){
                    if (!table[sor][oszlop] && !table[sor][oszlop+1]){
                        table[sor][oszlop] = true;
                        table[sor][oszlop+1]= true;
                        return 2;
                    }
                }
                return 0;
            case "F5":
                if(sor > 1 && sor < 8){
                    if (!table[sor-2][oszlop] && !table[sor-1][oszlop] && !table[sor][oszlop] && !table[sor+1][oszlop] && !table[sor+2][oszlop]){
                        table[sor-2][oszlop] = true;
                        table[sor-1][oszlop] = true;
                        table[sor][oszlop] = true;
                        table[sor+1][oszlop]= true;
                        table[sor+2][oszlop]=true;
                        return 5;
                    }
                }
                return 0;
            case "F4":
                if(sor > 0 && sor < 8){
                    if (!table[sor-1][oszlop] && !table[sor][oszlop] && !table[sor+1][oszlop] && !table[sor+2][oszlop]){
                        table[sor-1][oszlop] = true;
                        table[sor][oszlop] = true;
                        table[sor+1][oszlop]= true;
                        table[sor+2][oszlop]=true;
                        return 4;
                    }
                }
                return 0;
            case "F3":
                if(sor > 0 && sor < 9){
                    if (!table[sor-1][oszlop] && !table[sor][oszlop] && !table[sor+1][oszlop]){
                        table[sor-1][oszlop] = true;
                        table[sor][oszlop] = true;
                        table[sor+1][oszlop]= true;
                        return 3;
                    }
                }
                return 0;
            case "F2":
                if(sor > 0){
                    if (!table[sor][oszlop] && !table[sor-1][oszlop]){
                        table[sor][oszlop] = true;
                        table[sor-1][oszlop]= true;
                        return 2;
                    }
                }
                return 0;
            case "N3":
                if (sor > 0 && sor < 9 && oszlop > 0 && oszlop < 9){
                    boolean joE = true;
                    for (int i = sor - 1; i < sor + 2; i++) {
                        for (int j = oszlop - 1; j < oszlop + 2; j++) {
                            if(table[i][j]){
                                joE = false;
                                break;
                            }
                        }
                        if (!joE){
                            break;
                        }
                    }
                    if (joE){
                        for (int i = sor - 1; i < sor + 2; i++) {
                            for (int j = oszlop - 1; j < oszlop + 2; j++) {
                                table[i][j] = true;
                            }
                        }
                        return 9;
                    }
                }
                return 0;
            case "N2":
                if (sor > 0  && oszlop < 9) {
                    if (!table[sor][oszlop] && !table[sor - 1][oszlop] && !table[sor][oszlop + 1] && !table[sor - 1][oszlop + 1]) {
                        table[sor][oszlop] = true;
                        table[sor - 1][oszlop] = true;
                        table[sor][oszlop + 1] = true;
                        table[sor - 1][oszlop + 1] = true;
                        return 4;
                    }
                }
                return 0;
            case "N1":
                if (!table[sor][oszlop]){
                    table[sor][oszlop] = true;
                    return 1;
                }
                return 0;
            case "LBA2":
                if (sor > 0 && oszlop < 9){
                    if (!table[sor][oszlop] && !table[sor-1][oszlop] && !table[sor][oszlop+1]){
                        table[sor][oszlop] = true;
                        table[sor-1][oszlop] = true;
                        table[sor][oszlop+1] = true;
                        return 3;
                    }
                }
                return 0;
            case "LBF2":
                if (sor < 9 && oszlop < 9){
                    if (!table[sor][oszlop] && !table[sor+1][oszlop] && !table[sor][oszlop+1]){
                        table[sor][oszlop] = true;
                        table[sor+1][oszlop] = true;
                        table[sor][oszlop+1] = true;
                        return 3;
                    }
                }
                return 0;
            case "LJA2":
                if (sor > 0 && oszlop > 0){
                    if (!table[sor][oszlop] && !table[sor-1][oszlop] && !table[sor][oszlop-1]){
                        table[sor][oszlop] = true;
                        table[sor-1][oszlop] = true;
                        table[sor][oszlop-1] = true;
                        return 3;
                    }
                }
                return 0;
            case "LJF2":
                if (sor < 9 && oszlop > 0){
                    if (!table[sor][oszlop] && !table[sor+1][oszlop] && !table[sor][oszlop-1]){
                        table[sor][oszlop] = true;
                        table[sor+1][oszlop] = true;
                        table[sor][oszlop-1] = true;
                        return 3;
                    }
                }
                return 0;
            case "LBA3":
                if (sor > 1 && oszlop < 8){
                    if (!table[sor][oszlop] && !table[sor-1][oszlop] && !table[sor-2][oszlop] && !table[sor][oszlop+1] && !table[sor][oszlop+2]){
                        table[sor][oszlop] = true;
                        table[sor-1][oszlop] = true;
                        table[sor-2][oszlop] = true;
                        table[sor][oszlop+1] = true;
                        table[sor][oszlop+2] = true;
                        return 5;
                    }
                }
                return 0;
            case "LBF3":
                if (sor < 8 && oszlop < 8){
                    if (!table[sor][oszlop] && !table[sor+1][oszlop] && !table[sor+2][oszlop] && !table[sor][oszlop+1] && !table[sor][oszlop+2]){
                        table[sor][oszlop] = true;
                        table[sor+1][oszlop] = true;
                        table[sor+2][oszlop] = true;
                        table[sor][oszlop+1] = true;
                        table[sor][oszlop+2] = true;
                        return 5;
                    }
                }
                return 0;
            case "LJA3":
                if (sor > 1 && oszlop > 1){
                    if (!table[sor][oszlop] && !table[sor-1][oszlop] && !table[sor-2][oszlop] && !table[sor][oszlop-1] && !table[sor][oszlop-2]){
                        table[sor][oszlop] = true;
                        table[sor-1][oszlop] = true;
                        table[sor-2][oszlop] = true;
                        table[sor][oszlop-1] = true;
                        table[sor][oszlop-2] = true;
                        return 5;
                    }
                }
                return 0;
            case "LJF3":
                if (sor < 8 && oszlop > 1){
                    if (!table[sor][oszlop] && !table[sor+1][oszlop] && !table[sor+2][oszlop] && !table[sor][oszlop-1] && !table[sor][oszlop-2]){
                        table[sor][oszlop] = true;
                        table[sor+1][oszlop] = true;
                        table[sor+2][oszlop] = true;
                        table[sor][oszlop-1] = true;
                        table[sor][oszlop-2] = true;
                        return 5;
                    }
                }
                return 0;
            default:
                return 0;
        }
    }

    public int beteltE(int pont) {
        Jatek jatek = new Jatek();
        int pluszPont = pont;
        boolean talalt = true;
        //sorok
        for (int i = 0; i < 10; i++) {
            talalt = true;
            for (int j = 0; j < 10; j++) {
                if (!table[i][j]){
                    talalt = false;
                    break;
                }
            }
            if (talalt){
                pluszPont += 10;
                for (int j = 0; j < 10; j++) {
                    jatek.table[i][j] = true;
                }
            }
        }
        //oszlopok
        for (int j = 0; j < 10; j++) {
            talalt = true;
            for (int i = 0; i < 10; i++) {
                if(!table[i][j]){
                    talalt = false;
                    break;
                }
            }
            if (talalt){
                pluszPont += 10;
                for (int i = 0; i < 10; i++) {
                    jatek.table[i][j] = true;
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (jatek.table[i][j] && jatek.table[i][j] == table[i][j]){
                    table[i][j] = false;
                }
            }
        }
        pontszam += pluszPont;
        return pluszPont;
    }

    public void ujElemek(){
        for (int i = 0; i < lathatoElemek.length; i++) {
            lathatoElemek[i] = elemek[(int)(Math.random() * elemek.length)];
        }
    }

    public boolean vesztettE(){
        if((torlesSegitseg != 0 || ujElemSegitseg != 0) && pontszam >= 50)
            return false;
        for (String elem:lathatoElemek) {
            if (elem != null){
                if(beferE(elem)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean beferE(String elem){
        switch (elem){
            case "V5": {
                int talaltHely;
                for (int i = 0; i <10; i++) {
                    talaltHely = 0;
                    for (int j = 0; j < 10; j++) {
                        if (!table[i][j]){
                            talaltHely++;
                        }else {
                            talaltHely = 0;
                        }
                        if (talaltHely == 5){
                            return true;
                        }
                    }
                }
                return false;
            }
            case "V4": {
                int talaltHely;
                for (int i = 0; i <10; i++) {
                    talaltHely = 0;
                    for (int j = 0; j < 10; j++) {
                        if (!table[i][j]){
                            talaltHely++;
                        }else {
                            talaltHely = 0;
                        }
                        if (talaltHely == 4){
                            return true;
                        }
                    }
                }
                return false;
            }
            case "V3": {
                int talaltHely;
                for (int i = 0; i <10; i++) {
                    talaltHely = 0;
                    for (int j = 0; j < 10; j++) {
                        if (!table[i][j]){
                            talaltHely++;
                        }else {
                            talaltHely = 0;
                        }
                        if (talaltHely == 3){
                            return true;
                        }
                    }
                }
                return false;
            }
            case "V2": {
                int talaltHely;
                for (int i = 0; i <10; i++) {
                    talaltHely = 0;
                    for (int j = 0; j < 10; j++) {
                        if (!table[i][j]){
                            talaltHely++;
                        }else {
                            talaltHely = 0;
                        }
                        if (talaltHely == 2){
                            return true;
                        }
                    }
                }
                return false;
            }
            case "F5": {
                int talaltHely;
                for (int i = 0; i <10; i++) {
                    talaltHely = 0;
                    for (int j = 0; j < 10; j++) {
                        if (!table[j][i]){
                            talaltHely++;
                        }else {
                            talaltHely = 0;
                        }
                        if (talaltHely == 5){
                            return true;
                        }
                    }
                }
                return false;
            }
            case "F4": {
                int talaltHely;
                for (int i = 0; i <10; i++) {
                    talaltHely = 0;
                    for (int j = 0; j < 10; j++) {
                        if (!table[j][i]){
                            talaltHely++;
                        }else {
                            talaltHely = 0;
                        }
                        if (talaltHely == 4){
                            return true;
                        }
                    }
                }
                return false;
            }
            case "F3": {
                int talaltHely;
                for (int i = 0; i <10; i++) {
                    talaltHely = 0;
                    for (int j = 0; j < 10; j++) {
                        if (!table[j][i]){
                            talaltHely++;
                        }else {
                            talaltHely = 0;
                        }
                        if (talaltHely == 3){
                            return true;
                        }
                    }
                }
            }
            case "F2": {
                int talaltHely;
                for (int i = 0; i <10; i++) {
                    talaltHely = 0;
                    for (int j = 0; j < 10; j++) {
                        if (!table[j][i]){
                            talaltHely++;
                        }else {
                            talaltHely = 0;
                        }
                        if (talaltHely == 2){
                            return true;
                        }
                    }
                }
                return false;
            }
            case "N3": {
                int talaltHely;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        talaltHely = 0;
                        for (int k = i; k < i+3; k++) {
                            for (int l = j; l < j+3; l++) {
                                if(!table[k][l]){
                                    talaltHely++;
                                }
                            }
                        }
                        if (talaltHely == 9){
                            return true;
                        }
                    }
                }
                return false;
            }
            case "N2": {
                int talaltHely;
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        talaltHely = 0;
                        for (int k = i; k < i+2; k++) {
                            for (int l = j; l < j+2; l++) {
                                if(!table[k][l]){
                                    talaltHely++;
                                }
                            }
                        }
                        if (talaltHely == 4){
                            return true;
                        }
                    }
                }
                return false;
            }
            case "N1": {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (!table[i][j]) {
                            return true;
                        }
                    }
                }
                return false;
            }
            case "LBA2": {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (!table[i][j] && !table[i+1][j] && !table[i+1][j+1]){
                            return true;
                        }
                    }
                }
                return false;
            }
            case "LBF2": {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (!table[i][j] && !table[i+1][j] && !table[i][j+1]){
                            return true;
                        }
                    }
                }
                return false;
            }
            case "LJA2": {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (!table[i][j+1] && !table[i+1][j] && !table[i+1][j+1]){
                            return true;
                        }
                    }
                }
                return false;
            }
            case "LJF2": {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (!table[i][j] && !table[i][j+1] && !table[i+1][j+1]){
                            return true;
                        }
                    }
                }
                return false;
            }
            case "LBA3": {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (!table[i][j] && !table[i+1][j] && !table[i+2][j] && !table[i+2][j+1] && !table[i+2][j+2]){
                            return true;
                        }
                    }
                }
                return false;
            }
            case "LBF3": {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (!table[i][j] && !table[i+1][j] && !table[i+2][j] && !table[i][j+1] && !table[i][j+2]){
                            return true;
                        }
                    }
                }
                return false;
            }
            case "LJA3": {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (!table[i][j+2] && !table[i+1][j+2] && !table[i+2][j+2] && !table[i+2][j+1] && !table[i+2][j]){
                            return true;
                        }
                    }
                }
                return false;
            }
            case "LJF3": {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (!table[i][j] && !table[i][j+1] && !table[i][j+2] && !table[i+1][j+2] && !table[i+2][j+2]){
                            return true;
                        }
                    }
                }
                return false;
            }
            default:
                return false;
        }
    }

    public void sorTorles(int sor){
        for (int i = 0; i < 10; i++) {
            table[sor][i] = false;
        }
    }

    public void oszlopTorles(int oszlop){
        for (int i = 0; i < 10; i++) {
            table[i][oszlop] = false;
        }
    }
}

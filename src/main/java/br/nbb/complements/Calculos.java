package br.nbb.complements;

public class Calculos {
	
    public boolean isNumb(String str) {
        return str != null && str.replace(',', '.').matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    public Double convertDb(String str) {
        return isNumb(str) ? Double.parseDouble(str.replace(',', '.')) : 0D;
    }

}

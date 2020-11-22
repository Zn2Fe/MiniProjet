public final class Terrain {
    public static final int NBLIGNESMAX = 20;
    public static final int NBCOLONNESMAX = 20;
    private static final int NBCARAFFICHES = 5;
    public final int nbLignes;
    public final int nbColonnes;
    private final Ressource[][] terrain;

    public Terrain() {
        this(20, 20);
    }

    public Terrain(int var1, int var2) {
        if (var1 > NBLIGNESMAX) {
            this.nbLignes = 20;
        } else if (var1 <= 0) {
            this.nbLignes = 1;
        } else {
            this.nbLignes = var1;
        }

        if (var2 > NBCOLONNESMAX) {
            this.nbColonnes = 20;
        } else if (var2 <= 0) {
            this.nbColonnes = 1;
        } else {
            this.nbColonnes = var2;
        }

        this.terrain = new Ressource[this.nbLignes][this.nbColonnes];
    }

    public Ressource getCase(int var1, int var2) {
        return this.sontValides(var1, var2) ? this.terrain[var1][var2] : null;
    }

    public Ressource videCase(int var1, int var2) {
        if (this.sontValides(var1, var2) && this.terrain[var1][var2] != null) {
            Ressource var3 = this.terrain[var1][var2];
            var3.initialisePosition();
            this.terrain[var1][var2] = null;
            return var3;
        } else {
            return null;
        }
    }

    public boolean setCase(int var1, int var2, Ressource var3) {
        if (this.sontValides(var1, var2)) {
            if (this.terrain[var1][var2] != null) {
                this.terrain[var1][var2].initialisePosition();
            }

            this.terrain[var1][var2] = var3;
            var3.setPosition(var1, var2);
            return true;
        } else {
            return false;
        }
    }

    public boolean caseEstVide(int var1, int var2) {
        if (this.sontValides(var1, var2)) {
            return this.terrain[var1][var2] == null;
        } else {
            return true;
        }
    }

    public boolean sontValides(int var1, int var2) {
        return var1 >= 0 && var1 < this.nbLignes && var2 >= 0 && var2 < this.nbColonnes;
    }

    public void affiche() {
        String var1 = "";
        String var2 = ":";
        String var3 = "";

        int var4;
        for(var4 = 0; var4 < 5; ++var4) {
            var3 = var3 + "-";
        }

        for(var4 = 0; var4 < this.nbColonnes; ++var4) {
            var2 = var2 + var3 + ":";
        }

        var2 = var2 + "\n";
        var1 = var2;

        for(var4 = 0; var4 < this.nbLignes; ++var4) {
            for(int var5 = 0; var5 < this.nbColonnes; ++var5) {
                if (this.terrain[var4][var5] == null) {
                    var1 = var1 + "|" + String.format("%-5s", " ");
                } else {
                    var1 = var1 + "|" + this.premiersCar(this.terrain[var4][var5].type);
                }
            }

            var1 = var1 + "|\n" + var2;
        }

        System.out.println(var1);
    }

    public String toString() {
        int var1 = 0;

        for(int var2 = 0; var2 < this.nbLignes; ++var2) {
            for(int var3 = 0; var3 < this.nbColonnes; ++var3) {
                if (this.terrain[var2][var3] != null) {
                    ++var1;
                }
            }
        }

        String var4 = "Terrain de " + this.nbLignes + "x" + this.nbColonnes + " cases: ";
        if (var1 == 0) {
            var4 = var4 + "toutes les cases sont libres.";
        } else if (var1 == 1) {
            var4 = var4 + "il y a une case occupée.";
        } else {
            var4 = var4 + "il y a " + var1 + " cases occupées.";
        }

        return var4;
    }

    private String premiersCar(String var1) {
        String var2 = String.format("%-5s", var1);
        return var2.substring(0, 5);
    }
}

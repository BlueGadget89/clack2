package clack.cipher;

public class ManualVignere {
    public static void main(String[] args)
    {
        String Str = "THEGOLDBUG";
        String Keyword = "EDGARALLANPOE";

        VignereCipher vc = new VignereCipher(Keyword);
        String encruption = vc.encrypt(Str);

        System.out.println(encruption);

    }
}


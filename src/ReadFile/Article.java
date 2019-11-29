package ReadFile;

public class Article {
    private  static int i=0;
    String DANCO;
    String info;

    public Article(String DANCO, String info) {
        this.DANCO = DANCO;
        this.info = info;
        System.out.println(i+"---------------"+DANCO);
        i++;
}


    public String getText(){
        String text = info.substring(info.indexOf("<TEXT>"), info.lastIndexOf("</TEXT>"));
        text = text.replace("<TEXT>", "");
        return text;
    }
}

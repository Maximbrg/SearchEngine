package ReadFile;

public class Article {

    String DANCO;
    String info;

    public Article(String DANCO, String info) {
        this.DANCO = DANCO;
        this.info = info;
        System.out.println("GITCHECK");
}


    public String getText(){
        String text = info.substring(info.indexOf("<TEXT>"), info.lastIndexOf("</TEXT>"));
        text = text.replace("<TEXT>", "");
        return text;
    }
}

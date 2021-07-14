import java.util.ArrayList;

public class ArticleFileOperations {

    /* This method reads an article file,
       creates Article objects according to the data obtained from the file content,
       and adds the objects to the articleArray */
    public static void readArticleFile(String fileName) {
        ArrayList<String> articleDataLines = MyFileIOOperations.readFile(fileName);

        assert articleDataLines != null;
        for (String articleDataLine : articleDataLines) {
            if (articleDataLine.startsWith("ARTICLE")) {
                String[] articleData = articleDataLine.split(" ");

                Article newArticle = new Article(Integer.parseInt(articleData[1]),
                        articleData[2], articleData[3], Integer.parseInt(articleData[4]));

                ArrayList<Integer> existingArticleIDs = new ArrayList<>();
                for (Article existingArticleObj : Article.articleArray) {
                    existingArticleIDs.add(existingArticleObj.getARTICLE_ID());
                }

                if (!existingArticleIDs.contains(newArticle.getARTICLE_ID())) {
                    Article.articleArray.add(newArticle);
                }
            }
        }
    }

    /* this method takes and Author object as parameter and
       prepares the article data to be written to the output file */
    public static void writeArticleLines(Author theAuthor) {
        for (int articleID : theAuthor.getAuthorArticleIDs()) {
            for (Article theArticle : Article.articleArray) {
                if (theArticle.getARTICLE_ID() == articleID) {
                    MyFileIOOperations.writeFile("\n+" + articleID + "\t" + theArticle.getARTICLE_NAME() + "\t" +
                            theArticle.getARTICLE_PUBLISHER_NAME() + "\t" + theArticle.getARTICLE_PUBLISH_YEAR());
                }
            }
        }
        MyFileIOOperations.writeFile("\n");
    }
}

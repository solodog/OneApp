package util;

import com.example.a17524.myapplication.R;

import java.util.List;

/**
 * Created by 17524 on 2019/10/14.
 */

public class datas {
    private String code;
    private String message;
    public  List<Result> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public class Result{
        public String getAuthor() {
            return author;
        }

        public String getLink() {
            return link;
        }

        public String getPic() {
            return pic;
        }

        public String getType() {
            return type;
        }

        public String getTitle() {
            return title;
        }

        public String getLrc() {
            return lrc;
        }

        public String getSongid() {
            return songid;
        }

        public String getUrl() {
            return url;
        }
        public void setAuthor(String author) {
            this.author = author;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setLrc(String lrc) {
            this.lrc = lrc;
        }

        public void setSongid(String songid) {
            this.songid = songid;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        public String author;
        public String link;
        public String pic;
        public String type;
        public String title;
        public String lrc;
        public String songid;
        public String url;
    }
}

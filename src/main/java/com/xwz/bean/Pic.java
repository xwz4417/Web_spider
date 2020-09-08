package com.xwz.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * (Pic)实体类
 *
 * @author makejava
 * @since 2020-08-20 11:30:34
 */
public class Pic implements Serializable {
    private static final long serialVersionUID = -67793025645931441L;

    private Integer id;

    private String filename;

    private String href;

    private String title;

    private Date createtime;

    public Pic(Integer id, String filename, String href, String title, Date createtime) {
        this.id = id;
        this.filename = filename;
        this.href = href;
        this.title = title;
        this.createtime = createtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.createtime = format.parse(format.format(createtime));
    }

}
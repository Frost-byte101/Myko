package com.watcher.utils;

import org.json.JSONObject;

public interface IJson {

    default String trans(String str) {

        JSONObject jsonObject=new JSONObject();

        String[] split = str.split("\\{")[1].split("}")[0].split(",");
        for (String s : split) {
            String[] split1 = s.split("=");
            jsonObject.put(split1[0].trim(),split1[1].replaceAll("'","").trim());
        }

        return jsonObject.toString();
    }

    String toJson();

}

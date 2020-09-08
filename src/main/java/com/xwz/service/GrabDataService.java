package com.xwz.service;

import java.util.Map;

public interface GrabDataService {
Map<String,Integer> CollectPics(int fid, int start, int stop) throws Exception;
}

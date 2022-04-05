package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.ReplyDAO;
import com.atguigu.qqzone.pojo.HostReply;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.HostReplyService;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {

    private ReplyDAO replyDAO ;
    //此处引入的是其他POJO对应的Service接口，而不是DAO接口
    //其他POJO对应的业务逻辑是封装在service层的，我需要调用别人的业务逻辑方法，而不要去深入考虑人家内部的细节
    private HostReplyService hostReplyService ;
    private UserBasicService userBasicService ;


    @Override
    public List<Reply> getReplyListByTopicId(Integer topicId) {

        List<Reply> replyList = replyDAO.getReplyList(new Topic(topicId));
        for(int i = 0;i < replyList.size();i++){
            Reply reply = replyList.get(i);

            UserBasic author = userBasicService.getUserBasicById(reply.getAuthor().getId());
            reply.setAuthor(author);

            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            reply.setHostReply(hostReply);
        }
        return replyList;


    }

    @Override
    public void addReply(Reply reply) {
        replyDAO.addReply(reply);
    }

    @Override
    public void delReply(Integer id) {
        Reply reply = replyDAO.getReply(id);

        if(reply != null){
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());

            if(hostReply!=null){
                hostReplyService.delHostReply(hostReply.getId());
            }
            replyDAO.delReply(id);

        }
    }

    @Override
    public void delReplyList(Topic topic) {

        List<Reply> replyList = replyDAO.getReplyList(topic);

        if(replyList!=null){
            for(Reply reply : replyList){
                delReply(reply.getId());
            }
        }

    }
}

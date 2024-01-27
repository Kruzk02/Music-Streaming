package RedditClone.DTO;

import RedditClone.Model.VoteType;

public class VoteDTO {
    private VoteType voteType;
    private Long postId;

    public VoteDTO() {
    }

    public VoteDTO(VoteType voteType, Long postId) {
        this.voteType = voteType;
        this.postId = postId;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}

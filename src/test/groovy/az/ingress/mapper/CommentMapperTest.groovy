package az.ingress.mapper

import az.ingress.dao.entity.CommentEntity
import az.ingress.model.criteria.PageCriteria
import az.ingress.model.request.CreateOrUpdateCommentRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import spock.lang.Specification

import static az.ingress.mapper.CommentMapper.COMMENT_MAPPER

class CommentMapperTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()


    def "BuildCommentEntityTest"() {
        given:
        def request = random.nextObject(CreateOrUpdateCommentRequest)

        when:
        def commentEntity = CommentMapper.COMMENT_MAPPER.buildCommentEntity(request)

        then:
        request.content == commentEntity.content
        request.productId == commentEntity.productId
        request.userId == commentEntity.userId
    }

    def "BuildCommentResponseTest"() {
        given:
        def entity = random.nextObject(CommentEntity)
        when:
        def commentResponse = CommentMapper.COMMENT_MAPPER.buildCommentResponse(entity)
        then:
        entity.content == commentResponse.content
        entity.productId == commentResponse.productId
        entity.userId == commentResponse.userId
    }


    def "UpdateCommentTest"() {
        given:
        def entity = random.nextObject(CommentEntity)
        def request = random.nextObject(CreateOrUpdateCommentRequest)
        when:
        CommentMapper.COMMENT_MAPPER.updateComment(entity, request)
        then:
        entity.content == request.content
    }

    def "BuildPageableResponseForCommentsTest"() {
        given:
        def total = 1L
        def pageCriteria = new PageCriteria(0, 10)
        def pageable = PageRequest.of(pageCriteria.getPage(), pageCriteria.getSize())

        def entity = new CommentEntity(
                id: 1L,
                userId: 123L,
                productId: 456L,
                content: "Test comment"
        )
        def pageOfComments = new PageImpl([entity], pageable, total)

        when:
        def pageableResponse = COMMENT_MAPPER.buildPageableResponse(pageOfComments)

        then:
        pageableResponse.content.size() == 1
        pageableResponse.totalElements == total
        pageableResponse.totalPages == 1
        !pageableResponse.hasNextPage

        def response = pageableResponse.content[0]
        response.userId == entity.userId
        response.productId == entity.productId
        response.content == entity.content

    }
}
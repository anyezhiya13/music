<template>
    <div class="search-song-list">
      <content-list :contentList="albumDatas"></content-list>
    </div>
</template>
<script>
import { getSongListOfLikeTitle } from '../../api'
import ContentList from '../ContentList.vue'
import { mixin } from '../../mixins'
export default {
  components: {
    ContentList
  },
  name: 'search-song-lists',
  mixins: [mixin],
  data () {
    return {
      albumDatas: []
    }
  },
  mounted () {
    this.getSearchList()
  },
  methods: {
    getSearchList () {
      if (!this.$route.query.keywords) {
        this.notify('您输入的内容为空', 'warning')
      } else {
        getSongListOfLikeTitle(this.$route.query.keywords)
          .then(res => {
            if (res) {
              this.albumDatas = res
            } else {
              this.notify('暂无该歌单内容', 'warning')
            }
          })
      }
    }
  }
}
</script>
<style lang="scss" scoped>
@import '../../assets/css/search-song-Lists.scss'
</style>

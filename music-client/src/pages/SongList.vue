<template>
  <div class="song-list">
    <ul class="song-list-header">
      <li v-for="(item,index) in songStyle" :key="index" @click="handleChangeView(item.name)" :class="{active: item.name==activeName}">
      {{item.name}}
      </li>
    </ul>
    <div>
      <content-list :contentList="data"></content-list>
      <div class="pagination">
        <el-pagination @current-change="handleCurrentChange" background layout="total,prev,pager,next"
          :current-page="currentPage" :page-size="pageSize" :total="albumDatas.length"></el-pagination>
      </div>
    </div>
  </div>
</template>
<script>
import { getAllSongList, getSongListOfLikeStyle } from '../api/index'
import ContentList from '../components/ContentList.vue'
import { mixin } from '../mixins'
import { songStyle } from '../assets/data/songList'
export default {
  components: {
    ContentList
  },
  name: 'song-list',
  mixins: [mixin],
  data () {
    return {
      albumDatas: [], // 歌单数据
      pageSize: 10, // 页面大小，一页15条数据
      currentPage: 1, // 当前页默认第一页
      songStyle: [], // 歌单的风格
      activeName: '全部歌单' // 当前风格

    }
  },
  computed: {
    // 计算当前页表格的数据
    data () {
      return this.albumDatas.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  },
  mounted () {
    this.songStyle = songStyle
    this.getSongList()
  },
  methods: {
    getSongList () {
      getAllSongList()
        .then(res => {
          this.currentPage = 1
          this.albumDatas = res
        })
    },
    // 获取当前页
    handleCurrentChange (val) {
      this.currentPage = val
    },
    // 根据style显示对应歌单
    handleChangeView (name) {
      this.activeName = name
      this.albumDatas = []
      if (name === '全部歌单') {
        this.getSongList()
      } else {
        this.goSongListOfStyle(name)
      }
    },
    // 根据style查询对应歌单
    goSongListOfStyle (style) {
      getSongListOfLikeStyle(style)
        .then(res => {
          this.currentPage = 1
          this.albumDatas = res
        })
    }
  }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/song-list.scss'
</style>

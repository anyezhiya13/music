<template>
    <div class="table">
        <div class="curmbs">
            <i class="el-icon-tickets">歌单歌曲信息</i>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button type="primary" size="mini" @click="delAll">批量删除</el-button>
                <el-input v-model="select_word" size="mini" placeholder="请输入歌曲名" class="handle-input"></el-input>
                <el-button type="primary" size="mini" @click="centerDialogVisible = true">添加歌曲</el-button>
            </div>
        </div>
        <el-table size="mini" ref="multipleTable" border style="width:100%" height="600px" :data="tableData" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column prop="name" label="歌手-歌名" align="center"></el-table-column>
            <el-table-column label="操作" width="150" align="center">
                <template slot-scope="scope">
                   <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
            <el-dialog title="添加歌曲" :visible.sync="centerDialogVisible" width="400px" center>
            <el-form :model="registerForm" ref="registerForm" label-width="80px" action="" id="tf">
               <el-form-item prop="singerName" label="歌手名字" size="mini">
                <el-input v-model="registerForm.singerName" placeholder="歌手名字"></el-input>
               </el-form-item>
               <el-form-item prop="songName" label="歌曲名字" size="mini">
                <el-input v-model="registerForm.songName" placeholder="歌曲名字"></el-input>
               </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
                <el-button size="mini" @click="getSongId">确定</el-button>
            </span>
        </el-dialog>
        <el-dialog title="删除歌曲" :visible.sync="deleVisible" width="400px" center>
            <div align="center">删除不可恢复，是否删除？</div>
            <span slot="footer">
                <el-button size="mini" @click="deleVisible = false">取消</el-button>
                <el-button size="mini" @click="deleteRow">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { mixin } from '../mixins/index'
import {delListSong, listSongAdd, listSongDetail, songOfSongId, songOfSongName} from '../api/index'
export default {
  mixins: [mixin],
  data () {
    return {
      centerDialogVisible: false, // 添加弹窗是否显示
      deleVisible: false, // 删除弹窗是否显示
      registerForm: { // 添加框
        singerName: '',
        songName: ''
      },
      tableData: [],
      tempData: [],
      select_word: '',
      idx: -1, // 当前选择项
      multipleSelection: [], // 那些项已经打勾了
      songListId: '' // 歌单Id
    }
  },
  watch: {
    select_word: function () {
      if (this.select_word === '') {
        this.tableData = this.tempData
      } else {
        this.tableData = []
        for (let item of this.tempData) {
          if (item.name.includes(this.select_word)) {
            this.tableData.push(item)
          }
        }
      }
    }
  },
  created () {
    this.songListId = this.$route.query.id
    this.getData()
  },
  methods: {
    // 查询所有歌手
    getData () {
      this.tableData = []
      this.tempData = []
      listSongDetail(this.songListId).then(res => {
        for (let item of res) {
          this.getSong(item.songId)
        }
      })
    },
    // 根据songId查询歌曲对象，放到tempData和tableData里面
    getSong (id) {
      songOfSongId(id)
        .then(res => {
          this.tempData.push(res)
          this.tableData.push(res)
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 添加歌曲前的准备，获取歌曲id
    getSongId () {
      let _this = this
      var songOfName = _this.registerForm.singerName + '-' + _this.registerForm.songName
      songOfSongName(songOfName)
        .then(res => {
          this.addSong(res[0].id)
        })
    },
    // 添加歌曲
    addSong (songId) {
      let params = new URLSearchParams()
      params.append('songId', songId)
      params.append('songListId', this.songListId)
      listSongAdd(params)
        .then(res => {
          if (res.code === 1) {
            this.getData()
            this.notify('添加成功', 'success')
          } else {
            this.notify('添加失败', 'error')
          }
        })
        .catch(err => {
          console.log(err)
        })
      this.centerDialogVisible = false
    },
    // 删除歌曲
    deleteRow () {
      delListSong(this.idx, this.songListId)
        .then(res => {
          if (res) {
            this.getData()
            this.notify('删除成功', 'success')
          } else {
            this.notify('删除失败', 'error')
          }
        })
        .catch(err => {
          console.log(err)
        })
      this.deleVisible = false
    }
  }
}

</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}

.handle-input {
    width: 330px;
    display: inline-block;
}
    .handle-input{
        width: 300px;
        display: inline-block;
    }
</style>
